<template>
  <div id="app">
    <header class="header">
      <div class="container">
        <h1 class="title">社群媒體平台</h1>
        <div class="auth-buttons" v-if="!currentUser">
          <button class="btn btn-primary" @click="showLogin = true">登入</button>
          <button class="btn btn-secondary" @click="showRegister = true">註冊</button>
        </div>
        <div class="user-info" v-else>
          <span class="welcome">歡迎, {{ currentUser.userName }} ({{ currentUser.email }})</span>
          <button class="btn btn-danger" @click="logout">登出</button>
        </div>
      </div>
    </header>

    <Login v-if="showLogin" @login="handleLogin" @close="showLogin = false" />
    <Register v-if="showRegister" @register="handleRegister" @close="showRegister = false" />

    <main class="main" v-if="currentUser">
      <div class="container">
        <PostForm @post-created="loadPosts" />
        <PostList :posts="posts" @post-updated="loadPosts" @post-deleted="loadPosts" />
      </div>
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
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #f8f9fa;
  color: #333;
}

#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 1rem 0;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title {
  font-size: 2rem;
  font-weight: 300;
}

.auth-buttons, .user-info {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: all 0.3s ease;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-secondary:hover {
  background-color: #545b62;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
}

.btn-danger:hover {
  background-color: #c82333;
}

.welcome {
  font-weight: 500;
}

.main {
  flex: 1;
  padding: 2rem 0;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 10px 30px rgba(0,0,0,0.3);
  max-width: 400px;
  width: 90%;
}

@media (max-width: 768px) {
  .container {
    flex-direction: column;
    gap: 1rem;
  }

  .title {
    font-size: 1.5rem;
  }
}
</style>
