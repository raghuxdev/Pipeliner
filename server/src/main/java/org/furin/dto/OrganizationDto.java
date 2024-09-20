package org.furin.dto;

import org.furin.entities.organization.Organization;

public class OrganizationDto {
    private final String displayName;
    private final int walletBalance;
    private final boolean isEnabled;
    private final int numberOfUsers;
    private final String adminEmail;

    public int getNoOfRoles() {
        return noOfRoles;
    }

    public void setNoOfRoles(int noOfRoles) {
        this.noOfRoles = noOfRoles;
    }

    private int noOfRoles;


    private OrganizationDto(Builder builder) {
        this.displayName = builder.displayName;
        this.walletBalance = builder.walletBalance;
        this.isEnabled = builder.isEnabled;
        this.numberOfUsers = builder.numberOfUsers;
        this.adminEmail = builder.adminEmail;
        this.noOfRoles=builder.noOfRoles;
    }

    public static Builder fromOrganization(Organization organization) {
        return new Builder()
                .displayName(organization.getDisplayName())
                .walletBalance(organization.getWalletBalance())
                .adminEmail(organization.getUser().getEmail())
                .numberOfUsers(organization.getUsers().size())
                .noOfRoles(organization.getUser().getRoles().size())
                .isEnabled(organization.isEnabled());
    }

    public static class Builder {

        private String displayName;
        private int walletBalance;
        private boolean isEnabled;
        private int numberOfUsers;
        private String adminEmail;
        private int noOfRoles;

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder walletBalance(int walletBalance) {
            this.walletBalance = walletBalance;
            return this;
        }

        public Builder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public Builder numberOfUsers(int numberOfUsers) {
            this.numberOfUsers = numberOfUsers;
            return this;
        }

        public Builder adminEmail(String adminEmail) {
            this.adminEmail = adminEmail;
            return this;
        }
        public Builder noOfRoles(int size) {
            this.noOfRoles = size;
            return this;
        }

        public OrganizationDto build() {
            return new OrganizationDto(this);
        }

    }

    // Getters for the fields
    public String getDisplayName() {
        return displayName;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public String getAdminEmail() {
        return adminEmail;
    }
}
