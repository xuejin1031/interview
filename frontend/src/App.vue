<template>
  <div id="app">
    <header>
      <h1>社群媒體平台</h1>
      <div v-if="!currentUser">
        <button @click="showLogin = true">登入</button>
        <button @click="showRegister = true">註冊</button>
      </div>
      <div v-else>
        <span>歡迎, {{ currentUser.userName }} ({{ currentUser.email }})</span>
        <button @click="logout">登出</button>
      </div>
    </header>

    <Login v-if="showLogin" @login="handleLogin" @close="showLogin = false" />
    <Register v-if="showRegister" @register="handleRegister" @close="showRegister = false" />

    <main v-if="currentUser">
      <PostForm @post-created="loadPosts" />
      <PostList :posts="posts" @post-updated="loadPosts" @post-deleted="loadPosts" />
    </main>
  </div>
</template>

<script>
import Login from './components/Login.vue'
import Register from './components/Register.vue'
import PostForm from './components/PostForm.vue'
import PostList from './components/PostList.vue'

export default {
  name: 'App',
  components: {
    Login,
    Register,
    PostForm,
    PostList
  },
  data() {
    return {
      currentUser: null,
      showLogin: false,
      showRegister: false,
      posts: []
    }
  },
  methods: {
    handleLogin(user) {
      this.currentUser = user;
      this.showLogin = false;
      this.loadPosts();
    },
    handleRegister(user) {
      this.currentUser = user;
      this.showRegister = false;
      this.loadPosts();
    },
    logout() {
      this.currentUser = null;
      this.posts = [];
    },
    async loadPosts() {
      try {
        const response = await fetch('http://localhost:8080/api/posts');
        this.posts = await response.json();
      } catch (error) {
        console.error('Error loading posts:', error);
      }
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

header {
  margin-bottom: 20px;
}

button {
  margin: 0 10px;
  padding: 10px 20px;
}
</style>
