INSERT INTO "citystatus" ("id", "label") VALUES (1, 'Commune simple');
INSERT INTO "citystatus" ("id", "label") VALUES (2, 'Chef-lieu canton');
INSERT INTO "citystatus" ("id", "label") VALUES (3, 'Préfecture');
INSERT INTO "citystatus" ("id", "label") VALUES (4, 'Sous-préfecture');
INSERT INTO "citystatus" ("id", "label") VALUES (5, 'Préfecture de région');
INSERT INTO "citystatus" ("id", "label") VALUES (6, 'Capitale d''état');

SELECT pg_catalog.setval('"citystatus_sequence"', 6, true);

commit;