import { userService } from '../_services';
import router from '../router';
import store from '../store'

const user = JSON.parse(localStorage.getItem('user'));
const state = user
    ? { status: { loggedIn: true }, user }
    : { status: {}, user: null };

const actions = {
    login({ commit }, { username, password }) {
        commit('loginRequest', { username });
        userService.login(username, password)
            .then(
                user => {
                    commit('loginSuccess', user);
                    store.state.authUser = user.path;
                    store.state.gdprConsent = user.consent;
                    if(!user.consent && !(user.path === '/user')){
                      console.log("GDPR CHECK!");
                    } else {
                      router.push(user.path)
                    }
                },
                error => {
                    commit('loginFailure', error);
                    console.log("Failed to login!")
                }
            );
    },
    logout({ commit }) {
        userService.logout();
        commit('logout');
    }
};

const mutations = {
    loginRequest(state, user) {
        state.status = { loggingIn: true };
        state.user = user;
    },
    loginSuccess(state, user) {
        state.status = { loggedIn: true };
        state.user = user;
    },
    loginFailure(state) {
        state.status = {};
        state.user = null;
    },
    logout(state) {
        state.status = { loggedIn: false};
        state.user = null;
    }
};

export const account = {
    namespaced: true,
    state,
    actions,
    mutations
};