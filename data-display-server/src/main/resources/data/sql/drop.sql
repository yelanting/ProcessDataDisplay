use data_display_server;

SELECT concat('DROP TABLE IF EXISTS ', table_name, ';')
FROM information_schema.tables
WHERE table_schema = 'data_display_server';

SET FOREIGN_KEY_CHECKS=0;
