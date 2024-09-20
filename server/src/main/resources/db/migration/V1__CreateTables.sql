--table creations
create table permissions
(id uuid primary key,
action varchar(255),
subject varchar(255),
conditions varchar[],
createdAt timestamp,
updatedAt timestamp);


create table roles
(id uuid primary key,
 key varchar(255),
 isEnabled boolean,
 description varchar(255),
 createdAt timestamp,
 updatedAt timestamp);

create table permission_role_mappings
(id uuid,
role_id uuid,permission_id uuid,
constraint fk_role_id foreign key (role_id) references roles(id) on delete set null  ,
constraint fk_permission_id foreign key (permission_id) references permissions(id) on delete set null
);

create table user_mfa_settings(
id uuid primary key,
isEmailMagicLinkEnabled varchar(255) default false,
mfaEnabled boolean default false,
lastOtp varchar(255)
);

create table users(
id uuid primary key,
username varchar(255),
email varchar(255) not null ,
phnNo varchar(255) null ,
isEmailVerified boolean default false ,
isPhoneVerified boolean default false,
organization_id uuid not null,
socketId varchar(255),
isEnabled boolean,
password varchar(255) not null,
mfasettings_id uuid,
constraint fk_mfa_settings_id  foreign key (mfasettings_id) references user_mfa_settings(id) on delete cascade
);




create table user_role_mappings(
id uuid,
user_id uuid,role_id uuid,
constraint fk_user_id foreign key (user_id) references users(id) on delete set null,
constraint fk_role_id foreign key (role_id) references roles(id) on delete set null
);


create table apps
(id uuid primary key,
 appName varchar(255),
 isEnabled boolean,
 allowCustomConnections boolean,
 isShared boolean,
 key varchar(225)
 );

 create table app_connections(
 id uuid primary key,
 app_id uuid ,
 appSecret varchar(225),
 appName varchar(255),
 token varchar(255),
 constraint fk_app_id foreign key (app_id) references apps(id) on delete set null
 );


create table pipelines(
id uuid primary key,
user_id uuid not null,
pipelineName varchar(255) not null,
isActive  boolean not null,
deletedAt timestamp,
updatedAt timestamp,
publishedAt timestamp,
createdAt timestamp,
remoteWebHookId varchar(255),
constraint fk_user_id foreign key (user_id) references users(id) on delete cascade
);

create table jobs(
id uuid primary key,
key varchar(255),
appKey varchar(255),
type varchar(255),
app_connection_id uuid,
parameters jsonb ,
createdAt timestamp,
updatedAt timestamp,
pipeline_id uuid,
position int ,
status varchar(255),
deletedAt timestamp,
webhookPath varchar(255),
constraint fk_app_connection_id foreign key (app_connection_id)  references app_connections(id) on delete  cascade,
constraint fk_pipeline_id foreign  key (pipeline_id) references pipelines(id) on delete cascade
);

create table pipeline_executions(
id uuid primary key,
pipeline_id uuid not null,
createdAt timestamp,
updatedAt timestamp,
deletedAt timestamp,
internalId uuid,
constraint fk_pipeline_id foreign  key (pipeline_id) references pipelines(id) on delete  cascade
);

create table job_executions(
id uuid primary key,
pipeline_id uuid not null,
job_id uuid ,
status varchar(255),
dataIn jsonb,
dataOut jsonb,
createdAt timestamp,
updatedAt timestamp,
errorDetails timestamp,
constraint fk_pipeline_id  foreign key (pipeline_id) references pipelines(id) on delete cascade,
constraint fk_job_id foreign  key (job_id) references jobs(id) on delete  cascade
);

create table organizations(
id uuid primary key,
isEnabled boolean,
key varchar(225) not null,
displayName varchar(255),
walletBalance int,
currency varchar(255),
user_id uuid ,
mfaEnabled boolean default false,
constraint fk_user_id foreign key (user_id) references users(id) on delete set null
);

create table organization_settings(
id uuid primary key,
organization_id uuid,
key varchar(225) not null ,
value varchar(225) not null ,
constraint fk_organization_id foreign key (organization_id) references organizations(id) on delete set null
);
