ALTER TABLE ONLY "city"
  ADD CONSTRAINT "PK_city" PRIMARY KEY ("id");

ALTER TABLE ONLY "citystatus"
  ADD CONSTRAINT "PK_citystatus" PRIMARY KEY ("id");

ALTER TABLE ONLY "district"
  ADD CONSTRAINT "PK_district" PRIMARY KEY ("id");

ALTER TABLE ONLY "person"
  ADD CONSTRAINT "PK_person" PRIMARY KEY ("id");

ALTER TABLE ONLY "region"
  ADD CONSTRAINT "PK_region" PRIMARY KEY ("region_id");

ALTER TABLE ONLY "sequence"
  ADD CONSTRAINT "PK_sequence" PRIMARY KEY ("seq_name");

ALTER TABLE ONLY "zipcode"
  ADD CONSTRAINT "PK_zipcode" PRIMARY KEY ("id");

ALTER TABLE ONLY "city"
  ADD CONSTRAINT "FK_city_city_status" FOREIGN KEY ("citystatus_id") REFERENCES "citystatus"("id");

ALTER TABLE ONLY "city"
  ADD CONSTRAINT "FK_city_district" FOREIGN KEY ("district_id") REFERENCES "district"("id");

ALTER TABLE ONLY "city"
  ADD CONSTRAINT "FK_city_region" FOREIGN KEY ("region_region_id") REFERENCES "region"("region_id");

ALTER TABLE ONLY "district"
  ADD CONSTRAINT "FK_district_region" FOREIGN KEY ("region_region_id") REFERENCES "region"("region_id");

ALTER TABLE ONLY "zipcode"
  ADD CONSTRAINT "FK_zipcode_city" FOREIGN KEY ("city_id") REFERENCES "city"("id");

commit;
