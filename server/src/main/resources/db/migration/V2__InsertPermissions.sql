CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO permissions (id, action, subject, conditions, createdAt, updatedAt)
VALUES
-- user CRUD permissions
 (gen_random_uuid(), 'create', 'users', '{}' , NOW(), NOW()),
(gen_random_uuid(),'update','users','{}',NOW(),NOW()),
(gen_random_uuid(),'read','users','{}',NOW(),NOW()),
(gen_random_uuid(),'delete','users','{}',NOW(),NOW()),
(gen_random_uuid(),'promote','users','{}',NOW(),NOW()),
--connection CRUD permissions
(gen_random_uuid(), 'create', 'connections', '{}' , NOW(), NOW()),
(gen_random_uuid(),'update','connections','{}',NOW(),NOW()),
(gen_random_uuid(),'read','connections','{}',NOW(),NOW()),
(gen_random_uuid(),'delete','connections','{}',NOW(),NOW()),
--Role CRUD permissions
(gen_random_uuid(), 'create', 'roles', '{}' , NOW(), NOW()),
(gen_random_uuid(),'update','roles','{}',NOW(),NOW()),
(gen_random_uuid(),'read','roles','{}',NOW(),NOW()),
(gen_random_uuid(),'delete','roles','{}',NOW(),NOW()),
--Organization CRUD permissions
(gen_random_uuid(),'create','organizations','{}',NOW(),NOW()),
(gen_random_uuid(),'update','organizations','{}',NOW(),NOW()),
(gen_random_uuid(),'delete','organizations','{}',NOW(),NOW()),
(gen_random_uuid(),'read','organizations','{}',NOW(),NOW()),
--Job CRUD permissions
(gen_random_uuid(),'create','jobs','{}',NOW(),NOW()),
(gen_random_uuid(),'update','jobs','{}',NOW(),NOW()),
(gen_random_uuid(),'delete','jobs','{}',NOW(),NOW()),
(gen_random_uuid(),'read','jobs','{}',NOW(),NOW());







