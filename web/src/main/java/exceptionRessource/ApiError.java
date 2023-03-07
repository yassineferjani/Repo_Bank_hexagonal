package exceptionRessource;

import java.time.OffsetDateTime;

public record ApiError(int status, String label, String message, String path, OffsetDateTime timestamp) {


    public static ApiErrorBuilder builder() {
        return new ApiErrorBuilder();
    }

    public static class ApiErrorBuilder {
        private int status;
        private String label;
        private String message;
        private String path;
        private OffsetDateTime timestamp;

        ApiErrorBuilder() {
        }

        public ApiErrorBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ApiErrorBuilder label(String label) {
            this.label = label;
            return this;
        }

        public ApiErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ApiErrorBuilder timestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiError build() {
            return new ApiError(status, label, message, path, timestamp);
        }

        public String toString() {
            return "ApiError.ApiErrorBuilder(status=" + this.status + ", label=" + this.label + ", message=" + this.message + ", path=" + this.path + ", timestamp=" + this.timestamp + ")";
        }
    }
}

