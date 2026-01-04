function ForgotPassword() {
  return (
    <div className="container">
      <div className="form-container card">
        <h2>Forgot Password</h2>

        <form>
          <input type="email" placeholder="Email" />
          <input type="password" placeholder="New Password" />

          <button type="submit" className="btn-primary">
            Reset Password
          </button>
        </form>
      </div>
    </div>
  );
}

export default ForgotPassword;
