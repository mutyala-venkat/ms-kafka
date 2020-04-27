#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE dbfulflmnt;
    GRANT ALL PRIVILEGES ON DATABASE dbfulflmnt TO dbuser;
    CREATE DATABASE dbfulflmnt1;
    GRANT ALL PRIVILEGES ON DATABASE dbfulflmnt1 TO dbuser;
    CREATE DATABASE dborder;
    GRANT ALL PRIVILEGES ON DATABASE dborder TO dbuser;
EOSQL
