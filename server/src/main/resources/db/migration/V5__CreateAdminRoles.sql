
insert into roles (id ,key ,isEnabled,description,createdAt,updatedAt)
values (gen_random_uuid(),'OrganizationAdmin','true','Admin role manages the specific organization',NOW(),NOW());
