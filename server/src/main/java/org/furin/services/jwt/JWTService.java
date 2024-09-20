package org.furin.services.jwt;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.furin.entities.permission.Permission;
import org.furin.entities.role.Role;
import org.furin.entities.user.User;

import java.util.*;
import java.util.stream.Collectors;

import static org.furin.constants.OrganizationConstants.ORGANIZATION_ID;
import static org.furin.constants.PermissionConstants.PERMISSIONS;
import static org.furin.constants.UserConstants.USER_ID;

@ApplicationScoped
public class JWTService {

    @Inject
    JWTParser parser;

    public String generateToken(User user) {
        long currentTimeInSec = currentTimeInSecs();
        Set<String> groups = new HashSet<>();
        List<Permission> uniquePermissions = removeDuplicatePermissionsAndAddRoles(user.getRoles(), groups);
        List<Map<String, Object>> validPermissions = getPermissions(uniquePermissions);

        JwtClaimsBuilder claimsBuilder = Jwt.claims()
                .issuedAt(currentTimeInSec)
                .subject(user.getEmail())
                .groups(groups)
                .claim(PERMISSIONS, validPermissions)
                .claim(USER_ID, user.getId())
                .claim(ORGANIZATION_ID, user.getOrganization().getId())
                .expiresAt(currentTimeInSec + 360000);

        return claimsBuilder.sign();
    }

    private static int currentTimeInSecs() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    private List<Map<String, Object>> getPermissions(List<Permission> permissions) {
        return permissions.stream()
                .map(permission -> {
                    Map<String, Object> permissionMap = new HashMap<>();
                    permissionMap.put("action", permission.getAction());
                    permissionMap.put("subject", permission.getSubject());
                    return permissionMap;
                })
                .collect(Collectors.toList());
    }

    private List<Permission> removeDuplicatePermissionsAndAddRoles(List<Role> roles, Set<String> groups) {
        Map<String, Permission> uniquePermissions = new HashMap<>();

        for (Role role : roles) {
            groups.add(role.getKey());
            for (Permission permission : role.getPermissions()) {
                String key = permission.getAction() + "_" + permission.getSubject();
                uniquePermissions.putIfAbsent(key, permission);
            }
        }
        return new ArrayList<>(uniquePermissions.values());
    }

    public JsonWebToken parseAndVerifyToken(String token){
        try{
            return parser.parse(token);
        }catch (ParseException parseException){
            System.out.println("Error while parsing Token");
            return null;
        }
    }
}
