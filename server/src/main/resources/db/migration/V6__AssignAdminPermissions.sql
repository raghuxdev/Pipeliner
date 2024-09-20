WITH admin_role AS (
select * from roles where key = 'OrganizationAdmin'
),
organization_admin_permissions AS (
select * from permissions where subject In ('roles','users','connections','jobs')
)
INSERT INTO permission_role_mappings(id,role_id,permission_id)
select gen_random_uuid(),
r.id,
p.id
from
admin_role r
cross join
organization_admin_permissions p;