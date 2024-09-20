package org.furin.spis.input;

public class InputConfig {

    private String name;
    private boolean required;
    private String description;

    private String key;
    private String value;
    private boolean visible;
    private String helpText;



    private InputConfig(Builder builder) {
        this.name = builder.name;
        this.required = builder.required;
        this.description = builder.description;

        this.key = builder.key;
        this.value = builder.value;
        this.visible = builder.visible;
        this.helpText = builder.helpText;
    }

    // Static inner class for builder pattern
    public static class Builder {
        private String name;
        private boolean required;
        private String description;

        private String key;
        private String value;
        private boolean visible;
        private String helpText;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder required(boolean required) {
            this.required = required;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }



        public Builder key(String key) {
            this.key = key;
            return this;
        }



        public Builder visible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public Builder helpText(String helpText) {
            this.helpText = helpText;
            return this;
        }

        public InputConfig build() {
            return new InputConfig(this);
        }
    }
}
