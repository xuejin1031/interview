<template>
  <div class="modal">
    <div class="modal-content">
      <h2 class="modal-title">登入</h2>
      <form @submit.prevent="login" class="form">
        <div class="form-group">
          <label for="email">電子郵件</label>
          <input id="email" v-model="email" type="email" required class="form-input">
        </div>
        <div class="form-group">
          <label for="password">密碼</label>
          <input id="password" v-model="password" type="password" required class="form-input">
        </div>
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">登入</button>
          <button type="button" class="btn btn-secondary" @click="$emit('close')">取消</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppLogin',
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

.modal-title {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
  font-weight: 300;
}

.form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
  color: #555;
}

.form-input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  transition: border-color 0.3s ease;
}

.form-input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 0 2px rgba(0,123,255,0.25);
}

.form-actions {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
  margin-top: 1rem;
}

.btn {
  padding: 0.75rem 1.5rem;
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
</style>