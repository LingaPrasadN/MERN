function ForgotPassword() {     
    return (
        <div>
            <h1>Forgot Password Page</h1>
            <form>
                <input type="email" placeholder="Email" /><br /> <br />
                <input type="password" placeholder="New Password" /><br /> <br />
                <button type="submit">Reset Password</button>
            </form>
        </div>
    );
}

export default ForgotPassword;