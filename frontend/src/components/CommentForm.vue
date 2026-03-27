<template>
  <div class="comment-form">
    <form @submit.prevent="createComment" class="form">
      <div class="form-group">
        <input v-model="content" type="text" placeholder="新增留言..." required class="form-input">
      </div>
      <button type="submit" class="btn btn-primary btn-sm">留言</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'CommentForm',
  props: ['postId'],
  data() {
    return {
      content: ''
    }
  },
  methods: {
    async createComment() {
      try {
        const response = await fetch('http://localhost:8080/api/comments', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            postId: this.postId,
            userId: this.$root.currentUser.id,
            content: this.content
          })
        });
        if (response.ok) {
          this.content = '';
          this.$emit('comment-created');
        }
      } catch (error) {
        console.error('Create comment error:', error);
      }
    }
  }
}
</script>

<style scoped>
.comment-form {
  margin-top: 1rem;
  padding-top: 1rem;
  border-top: 1px solid #eee;
}

.form {
  display: flex;
  gap: 0.5rem;
  align-items: center;
}

.form-group {
  flex: 1;
}

.form-input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 0.9rem;
  transition: border-color 0.3s ease;
}

.form-input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 0 2px rgba(0,123,255,0.25);
}

.btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 0.9rem;
  transition: all 0.3s ease;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-sm {
  padding: 0.4rem 0.8rem;
  font-size: 0.8rem;
}
</style>