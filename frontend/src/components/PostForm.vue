<template>
  <div class="post-form">
    <h3>新增發文</h3>
    <form @submit.prevent="createPost">
      <textarea v-model="content" placeholder="輸入內容..." required></textarea>
      <input v-model="image" type="text" placeholder="圖片 URL (選填)">
      <button type="submit">發佈</button>
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
  margin: 20px 0;
}

textarea {
  width: 100%;
  height: 100px;
  padding: 10px;
  margin-bottom: 10px;
}

button {
  padding: 10px 20px;
}
</style>