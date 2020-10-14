import Vue from "vue";
import VueRouter from "vue-router";
import Home from "./components/Home.vue";

Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		name: "Home",
		component: Home,
	},
	{
		path: "/Upcoming",
		name: "Upcoming",
		component: () => import("./components/Upcoming.vue"),
	},
	{
		path: "/Post",
		name: "Post",
		component: () => import("./components/Post.vue"),
	},
	{
		path: "/ManageUser",
		name: "ManageUser",
		component: () => import("./components/ManageUser.vue"),
	},
	{
		path: "/Login",
		name: "Login",
		component: () => import("./components/Login.vue"),
	},
	{
		path: "/Register",
		name: "Register",
		component: () => import("./components/Register.vue"),
	},
	{
		path: "/Content",
		name: "Content",
		props: true,
		component: () => import("./components/Content.vue"),
		beforeClick: (to, form, next) => {
			if (to.params.content_id) {
				next();
			} else {
				next({ name: "Home" });
			}
		},
	},
	{
		path: "/UserContent",
		name: "UserContent",
		props: true,
		component: () => import("./components/UserContent.vue"),
		beforeClick: (to, form, next) => {
			if (to.params.content_id) {
				next();
			} else {
				next({ name: "Home" });
			}
		},
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

export default router;
