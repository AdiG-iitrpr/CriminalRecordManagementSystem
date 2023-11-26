CREATE ROLE ADMIN_DB;

GRANT SELECT,INSERT,DELETE,UPDATE Criminal,courtHearing,jailLog,Jail,Cases,FIR,Officer,Suspect,Station,Users to ADMIN_DB;
GRANT ADMIN_DB to postgres;

CREATE ROLE JAILER;

GRANT SELECT,INSERT,UPDATE,DELETE on jailLog,Jail to JAILER;

CREATE ROLE PUBLIC;

GRANT SELECT on FIR,Cases,courtHearing,Jail,Station to PUBLIC;

CREATE ROLE COURT;

GRANT SELECT on courtHearing,Cases,Criminal,jailLog,Jail,Suspect,FIR,Station,Officer to COURT;
GRANT INSERT,UPDATE,DELETE on courtHearing,Cases,Criminal,jailLog,Jail to COURT;

CREATE ROLE POLICE;

GRANT SELECT,INSERT,UPDATE,DELETE on Station,FIR,Suspect,Officer to POLICE;