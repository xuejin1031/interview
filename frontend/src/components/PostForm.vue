<template>
  <div class="post-form card">
    <h3 class="card-title">新增發文</h3>
    <form @submit.prevent="createPost" class="form">
      <div class="form-group">
        <textarea v-model="content" placeholder="輸入內容..." required class="form-textarea"></textarea>
      </div>
      <div class="form-group">
        <input v-model="image" type="text" placeholder="圖片 URL (選填)" class="form-input">
      </div>
      <button type="submit" class="btn btn-primary">發佈</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'PostForm',
  data() {
    return {
      content: '',
      image: ''
    }
  },
  methods: {
    async createPost() {
      try {
        const response = await fetch('http://localhost:8080/api/posts', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            userId: this.$root.currentUser.id,
            content: this.content,
            image: this.image
          })
        });
        if (response.ok) {
          this.content = '';
          this.image = '';
          this.$emit('post-created');
        } else {
          alert('發佈失敗');
        }
      } catch (error) {
        console.error('Create post error:', error);
      }
    }
  }
}
</script>

<style scoped>
.post-form {
  margin-bottom: 2rem;
}

.card {
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  padding: 1.5rem;
}

.card-title {
  margin-bottom: 1rem;
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

.form-textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  resize: vertical;
  min-height: 100px;
  transition: border-color 0.3s ease;
}

.form-textarea:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 0 2px rgba(0,123,255,0.25);
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

.btn {
  align-self: flex-start;
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
</style>