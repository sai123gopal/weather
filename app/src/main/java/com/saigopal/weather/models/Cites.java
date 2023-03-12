package com.saigopal.weather.models;

import com.google.gson.annotations.SerializedName;

public class Cites {


        private String city;
        private String lat;
        private String lng;
        private String country;
        private String iso2;
        private String admin_name;
        private String capital;
        private String population;
        private String population_proper;


        // Getter Methods

        public String getCity() {
            return city;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getCountry() {
            return country;
        }

        public String getIso2() {
            return iso2;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public String getCapital() {
            return capital;
        }

        public String getPopulation() {
            return population;
        }

        public String getPopulation_proper() {
            return population_proper;
        }

        // Setter Methods

        public void setCity( String city ) {
            this.city = city;
        }

        public void setLat( String lat ) {
            this.lat = lat;
        }

        public void setLng( String lng ) {
            this.lng = lng;
        }

        public void setCountry( String country ) {
            this.country = country;
        }

        public void setIso2( String iso2 ) {
            this.iso2 = iso2;
        }

        public void setAdmin_name( String admin_name ) {
            this.admin_name = admin_name;
        }

        public void setCapital( String capital ) {
            this.capital = capital;
        }

        public void setPopulation( String population ) {
            this.population = population;
        }

        public void setPopulation_proper( String population_proper ) {
            this.population_proper = population_proper;
        }

}
