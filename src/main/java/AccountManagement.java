public interface AccountManagement {
    public boolean validatePassword(String enteredPassword);
    public void changeUsername(String newUsername);
    public boolean changePassword(String newPassword);
}
