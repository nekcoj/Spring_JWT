export function authHeader() {
    // return authorization header with jwt token
    let user = JSON.parse(localStorage.getItem('user'));

    if (user && user.token) {
        return { 'Authorization': 'Bearer ' + user.token };
    } else if (this.$store.state.token) {
        return {'Authorization': 'Bearer ' + this.$store.state.token}
    } 
    else {
        return {};
    }
}