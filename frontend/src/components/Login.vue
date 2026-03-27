<template>
  <div class="modal">
    <div class="modal-content">
      <h2>登入</h2>
      <form @submit.prevent="login">
        <input v-model="email" type="email" placeholder="電子郵件" required>
        <input v-model="password" type="password" placeholder="密碼" required>
        <button type="submit">登入</button>
        <button type="button" @click="$emit('close')">取消</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async login() {
      try {
        const response = await fetch('http://localhost:8080/api/users/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            email: this.email,
            password: this.password
          })
        });
        const data = await response.json();
        if (response.ok) {
          this.$emit('login', { id: data.userId, email: this.email, userName: 'User' }); // 簡化，實際應從後端獲取
        } else {
          alert(data.message);
        }
      } catch (error) {
        console.error('Login error:', error);
      }
    }
  }
}
</script>

<style scoped>
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
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
}

input {
  display: block;
  margin: 10px 0;
  padding: 10px;
  width: 200px;
}

button {
  margin: 10px 5px;
}
</style>