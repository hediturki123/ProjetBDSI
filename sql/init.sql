SET PAGESIZE 200;
SET LINESIZE 260;

ALTER SESSION SET nls_date_format='DD-MON-YY';

START dropPhotoNum.sql;
START createPhotoNum.sql;

START procedures/procExpirationImage.sql;

START triggers/trigExpirationImage.sql;