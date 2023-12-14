
-- Updated Databse Creation 2023/ December /6
Create Database Expenses
-- drop TABLE tag_budget
-- drop table expenses
-- drop table tag
-- drop table budget
-- drop TABLE app_user

Use Expenses
Go
CREATE TABLE app_user(
    username VARCHAR(20) PRIMARY KEY,
    name VARCHAR(30),
    email VARCHAR(40),
    PASSWORD VARCHAR(40)

)
Go
CREATE TABLE budget(
    bid int IDENTITY PRIMARY KEY,
    username VARCHAR(20) FOREIGN KEY REFERENCES app_user(username), -- Adding username to each new entery
    name VARCHAR(50),
    limit float,
    currency VARCHAR(20),
    old_currency VARCHAR(20),
    exchange_rate FLOAT,
    remaining float
)
Go
CREATE TABLE tag(
    --tid int IDENTITY PRIMARY KEY, --No need to make duplicate tag names when we have same username 
    username VARCHAR(20) FOREIGN KEY REFERENCES app_user(username), -- Adding username to each new tag
    tag_name VARCHAR(30),
    Constraint pk_tag PRIMARY key (
        username,
        tag_name
    )
)
Go

CREATE TABLE expenses(
    eid int IDENTITY PRIMARY KEY,
    tag_name VARCHAR(30) , -- Adding tag_name to each new entery
    username VARCHAR(20) FOREIGN KEY REFERENCES app_user(username), -- Adding username to each new entery
    bid int FOREIGN KEY REFERENCES budget(bid), -- Adding bid to each new entery
    createdDate datetime DEFAULT(getdate()),
    comment VARCHAR(250),
    amount float,
    CONSTRAINT fk_expenses_tag FOREIGN KEY (username, tag_name) REFERENCES tag(username, tag_name) -- Reference both columns in the composite key

)

Go

-- This table due to the many to many relation between budget and tag
Go
CREATE TABLE tag_budget(
    tag_name VARCHAR(30),
    username varchar(20),
    bid int,
    CONSTRAINT pk_tag_budget PRIMARY Key(
        tag_name,
        bid,
        username
    ),
    CONSTRAINT fk_budget FOREIGN KEY (bid) REFERENCES budget(bid),
    CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES app_user(username),
    CONSTRAINT fk_tag_budget_tag FOREIGN KEY (username, tag_name) REFERENCES tag(username, tag_name) -- Reference both columns in the composite key
)