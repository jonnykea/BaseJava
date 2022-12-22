INSERT INTO resume (uuid, full_name)
VALUES ('1', 'Jon'),
       ('2', 'Julia'),
       ('3', 'Max');

INSERT INTO contact (id, resume_uuid, type, value)
VALUES ('10', '1', 'phone', '914419'),
       ('11', '2', 'skype', '@skype'),
       ('12', '3', 'mail', '@mail.ru');

SELECT * FROM resume;
SELECT * FROM contact;

SELECT COUNT(uuid) FROM resume;

SELECT uuid, full_name FROM resume ORDER BY full_name ASC;

SELECT * From resume AS r
                  LEFT JOIN contact AS c on r.uuid = c.resume_uuid;

UPDATE contact SET type = '124'
WHERE resume_uuid = '07951ccb-508a-45fe-ae83-3488b61203c8';

UPDATE resume SET full_name = '123'
WHERE uuid = 'a455676f-802a-4fca-8462-116b0d78dee1';

SELECT *FROM contact
WHERE resume_uuid = 'a455676f-802a-4fca-8462-116b0d78dee1';

DELETE FROM resume WHERE uuid = '42ca1621-1780-487e-a542-6ec2ab30c85c';