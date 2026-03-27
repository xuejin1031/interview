<template>
  <div class="comment-form">
    <form @submit.prevent="createComment">
      <input v-model="content" type="text" placeholder="新增留言..." required>
      <button type="submit">留言</button>
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
  margin-top: 10px;
}

input {
  width: 70%;
  padding: 5px;
}

button {
  padding: 5px 10px;
}
</style>