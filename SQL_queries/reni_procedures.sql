CREATE PROCEDURE editing_tags(
    @username varchar(20), --  Specifiying user because there may be multiple users who have different tags + more secure
    @tagname varchar(20),
    @newtagname varchar(20),
    @operation char(1)
)
AS
BEGIN
    if(@operation='A')
    BEGIN
        Insert into tag (tag_name,username) VALUES (@tagname,@username); -- Username will be needed to be added according to the new modified schema
    END

    else if(@operation='D')
    BEGIN
    DELETE FROM tag WHERE tag_name=@tagname AND username = @username; -- Username will be needed to be added according to the new modified schema
    END

    else if(@operation='E')
    BEGIN
    UPDATE tag SET tag_name = @newtagname WHERE tag_name=@tagname  AND username = @username;  -- Username will be needed to be added according to the new modified schema
    END
END




------------------------------------------------------------------------------------------
GO
Create PROCEDURE editing_expenses(
    @username varchar(20), --  Specifiying user because there are  multiple users who have different expenses + more secure 
    @eid int = NULL,-- CHANGED 2.05
    @comment varchar(200),
    --@createdDate TIME, -- No need to add created time if the default value is when it's created 
    @amount int, 
    @tagname VARCHAR(20), -- User can assign a tag to it
    @bid int , -- User can assign a tag to it
    @operation char(1)
    
)
AS
BEGIN
    if(@operation='A')
    BEGIN
		declare @remaining float;

		SELECT @remaining = remaining
		FROM budget
		WHERE username = @username AND bid = @bid 
	
		if(@amount> @remaining)
		BEGIN
			return 'Out of remaining balance in budget'
		end
		--R shouldn't it add expense even if it is out of balance - just will show it with "-" and red color?
		ELSE
		BEGIN
			Insert into expenses (username,comment, amount,bid,tag_name) VALUES (@username,@comment, @amount,@bid,@tagname);  -- Updated the insert values -- CHANGED 2.05
			-- Updating remianing in budget after inserting the amoubt successfully 
			UPDATE budget
			Set remaining = remaining-@amount
			where username = @username AND bid = @bid

		END

	END
	else if(@operation='D')
	BEGIN
		DELETE FROM expenses WHERE eid=@eid and username = @username; -- Updated the delete condition More secure delete by adding username as input
	END

    else if(@operation='E')
    BEGIN

		declare @remaining2 float;
		SELECT @remaining = remaining
		FROM budget
		WHERE username = @username AND bid = @bid 


		-- adding the previous amount of expenses as we may change the previous amount with new amount. (Avoiding dublicates)
		SET @remaining = @remaining + (SELECT amount from expenses where eid = @eid);

		if(@amount> @remaining2)
		BEGIN
			return 'Out of remaining balance in budget'
		END	
		ELSE
		BEGIN

			UPDATE expenses SET comment = @comment, amount=@amount,tag_name = @tagname WHERE eid=@eid AND username = @username --createdDate=@createdDate WHERE eid=@eid; -- Updated the delete condition More secure delete by adding username as input + updating create date in update not good practice
		--R should here also be bid=@bid ?
		END

	END
END

---------------------------------------------------------------------------------------------------------------------------------------
-- I wasn't detailed in that one (sorry) I want it to give me a table with whole table of expenses for the user
GO
CREATE PROCEDURE get_expenses(
    @username varchar(20), --  Specifiying user because there will be multiple users + more secure
    --@eid int, 
    --@comment varchar(200),
    --@createdDate TIME,
    --@amount int,
    @operation char(1) -- No need for it but it is ok
)
AS
BEGIN
    if(@operation='G')
    BEGIN
    SELECT *
    FROM expenses 
    where username = @username; 
    END

END
