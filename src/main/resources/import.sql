INSERT INTO actor(actor_id, name, character, role) values(99,'brad pit', 'the cop', 'LEAD');
INSERT INTO actor(actor_id, name, character, role) values(98,'angelina jolie', 'alumn', 'LEAD');
INSERT INTO actor(actor_id, name, character, role) values(97,'zendaya', 'the nice', 'LEAD');

INSERT INTO video (video_id, title, synopsis, director, released_year, genre, running_time, content, impressions, views, status) VALUES ('6c327d34-cdff-4cda-9d74-8f927d89dc3a','The Matrix', 'Metaverso film', 'The Wachowskis', 1999, 'action', 5400, 'dummy', 0,0,'ACTIVE');
INSERT INTO video (video_id, title, synopsis, director, released_year, genre, running_time, content, impressions, views, status) VALUES ('6c327d34-cdff-4cda-9d74-8f927d89dc3b','Fight Club', 'a guy fights', 'David Fincher', 1999, 'action', 6000, 'dummy', 0,0,'ACTIVE');
INSERT INTO video (video_id, title, synopsis, director, released_year, genre, running_time, content, impressions, views, status) VALUES ('6c327d34-cdff-4cda-9d74-8f927d89dc3c','The Shawshank Redemption', 'about redemption', 'Frank Darabont', 1994, 'drama',6000, 'dummy', 0,0,'ACTIVE');

INSERT INTO video_actor(video_id, actor_id) values('6c327d34-cdff-4cda-9d74-8f927d89dc3a',99);
INSERT INTO video_actor(video_id, actor_id) values('6c327d34-cdff-4cda-9d74-8f927d89dc3b',98);
INSERT INTO video_actor(video_id, actor_id) values('6c327d34-cdff-4cda-9d74-8f927d89dc3c',97);

    
