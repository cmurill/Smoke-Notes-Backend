package dev.cmurillo.SmokeNotesBackend.auth;

public class AuthenticationResponse {

    private String token;

    protected AuthenticationResponse() {}

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationResponse that = (AuthenticationResponse) o;
        return token.equals(that.token);
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                '}';
    }

    public static class Builder {

        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }
    }

    public AuthenticationResponse build() {
        return new AuthenticationResponse(token);
    }
}
