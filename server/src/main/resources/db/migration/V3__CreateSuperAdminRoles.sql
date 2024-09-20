CREATE EXTENSION IF NOT EXISTS pgcrypto;

insert into roles (id ,key ,isEnabled,description,createdAt,updatedAt)
values (gen_random_uuid(),'SuperAdmin','true','Admin role manages all the organization everything',NOW(),NOW());
