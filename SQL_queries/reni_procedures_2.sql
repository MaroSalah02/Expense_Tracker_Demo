--R waits for Maro's check

Create PROCEDURE get_budget(
	@username varchar(20),
  	@operation char(1)
)
AS
BEGIN
    if(@operation='G')
    BEGIN
    SELECT *
    FROM budget
    where username = @username;
    END
END

---------------------------------------------------------------------------------------------------------------------------------------

GO
CREATE PROCEDURE editing_budget(
    @username varchar(20),
  	@name varchar(50),
    @limit float,
  	@bid int,
  	@currency varchar(20),
  	@operation char(1)
)
AS
BEGIN
-- R what about remaining? person will not be able to change it?
    if(@operation='A')
    BEGIN
    Insert into budget (username, name, LIMIT, currency, remaining) VALUES (@username, @name, @limit, @currency, @limit);  -- Updated the insert values
    END

    else if(@operation='E')
        BEGIN
        UPDATE budget SET name = @name, limit=@limit WHERE bid=@bid AND username = @username --createdDate=@createdDate WHERE eid=@eid; -- Updated the delete condition More secure delete by adding username as input + updating create date in update not good practice
    END

    else if(@operation='D')
    BEGIN
    DELETE FROM budget WHERE bid=@bid and username = @username; -- Updated the delete condition More secure delete by adding username as input
    END

END
---------------------------------------------------------------------------------------------------------------------------------------

Create PROCEDURE get_tags(
	@username varchar(20),
  	@operation char(1)
)
AS
BEGIN
    if(@operation='G')
    BEGIN
    SELECT *
    FROM tag
    where username = @username;
    END
END

---------------------------------------------------------------------------------------------------------------------------------------

GO
Create PROCEDURE login_user(
	@username varchar(20),
  	@password varchar(40),
  	@success bit output
)
AS
BEGIN
    if EXISTS (
    	SELECT 1
    	from app_user
    	where username = @username and PASSOWORD
    )
    Begin
    	SET @success = 1
    end
    ELSE
    BEGIN
    	SET @success = 0
    END
END