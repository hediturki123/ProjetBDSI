SET PAGESIZE 200;
SET LINESIZE 260;

ALTER SESSION SET nls_date_format='DD-MON-YY';

START dropPhotoNum.sql;
START createPhotoNum.sql;

START procedures/procExpiration.sql;
START procedures/procFacture.sql;
START procedures/procPromo.sql;

START triggers/trigFacture.sql;
START triggers/trigPromo.sql;