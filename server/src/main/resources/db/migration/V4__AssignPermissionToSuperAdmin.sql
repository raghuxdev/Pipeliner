-- Fetch all roles and permissions
-- with clause is used to store temp result set that can be referenced in main query.
-- here all_roles is a table that references the id and key from roles
WITH all_roles AS (
    SELECT id, key FROM roles
),
--here all_permissions is a table that references the id , action and subject from permission table
all_permissions AS (
    SELECT id, action, subject FROM permissions
)
INSERT INTO permission_role_mappings (id, role_id, permission_id)
SELECT
    gen_random_uuid(),  --  gen_random_uuid() generates a new UUID
    r.id AS role_id,
    p.id AS permission_id
FROM
    all_roles r
CROSS JOIN
    all_permissions p;
