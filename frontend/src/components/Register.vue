<template>
  <div class="modal">
    <div class="modal-content">
      <h2>註冊</h2>
      <form @submit.prevent="register">
        <input v-model="userName" type="text" placeholder="使用者名稱" required>
        <input v-model="email" type="email" placeholder="電子郵件" required>
        <input v-model="password" type="password" placeholder="密碼" required>
        <textarea v-model="biography" placeholder="自我介紹"></textarea>
        <button type="submit">註冊</button>
        <button type="button" @click="$emit('close')">取消</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    return {
      userName: '',
      email: '',
      password: '',
      biography: ''
    }
  },
  methods: {
    async register() {
      try {
        const response = await fetch('http://localhost:8080/api/users/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            userName: this.userName,
            email: this.email,
            password: this.password,
            biography: this.biography
          })
        });
        const data = await response.json();
        if (response.ok) {
          this.$emit('register', { userName: this.userName, email: this.email });
        } else {
          alert(data.message);
        }
      } catch (error) {
        console.error('Register error:', error);
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