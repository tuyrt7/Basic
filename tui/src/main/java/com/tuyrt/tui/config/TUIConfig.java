package com.tuyrt.tui.config;

public class TUIConfig {

    private boolean isDebug;
    private String logTag;

    public boolean isDebug() {
        return isDebug;
    }

    public String getLogTag() {
        return logTag;
    }

    private TUIConfig(Builder builder) {
        isDebug = builder.isDebug;
        logTag = builder.logTag;
    }


    public static final class Builder {
        private boolean isDebug;
        private String logTag;

        public Builder() {
        }

        public Builder isDebug(boolean val) {
            isDebug = val;
            return this;
        }

        public Builder logTag(String val) {
            logTag = val;
            return this;
        }

        public TUIConfig build() {
            return new TUIConfig(this);
        }
    }
}
