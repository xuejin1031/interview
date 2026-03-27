<template>
  <div class="post-list">
    <h3>所有發文</h3>
    <div v-for="post in posts" :key="post[0]" class="post">
      <p><strong>{{ post[4] }}</strong>: {{ post[1] }}</p>
      <small>{{ new Date(post[2]).toLocaleString() }}</small>
      <div v-if="canEdit(post)">
        <button @click="editPost(post)">編輯</button>
        <button @click="deletePost(post[0])">刪除</button>
      </div>
      <CommentForm :post-id="post[0]" @comment-created="$emit('post-updated')" />
      <CommentList :post-id="post[0]" />
    </div>
  </div>
</template>

<script>
import CommentForm from './CommentForm.vue'
import CommentList from './CommentList.vue'

export default {
  name: 'PostList',
  components: {
    CommentForm,
    CommentList
  },
  props: ['posts'],
  methods: {
    canEdit(post) {
      return this.$root.currentUser && this.$root.currentUser.phoneNumber === post[4];
    },
    editPost(post) {
      const newContent = prompt('編輯內容:', post[1]);
      if (newContent) {
        this.updatePost(post[0], newContent);
      }
    },
    async updatePost(id, content) {
      try {
        const response = await fetch(`http://localhost:8080/api/posts/${id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ content })
        });
        if (response.ok) {
          this.$emit('post-updated');
        }
      } catch (error) {
        console.error('Update post error:', error);
      }
    },
    async deletePost(id) {
      if (confirm('確定刪除?')) {
        try {
          const response = await fetch(`http://localhost:8080/api/posts/${id}`, {
            method: 'DELETE'
          });
          if (response.ok) {
            this.$emit('post-deleted');
          }
        } catch (error) {
          console.error('Delete post error:', error);
        }
      }
    }
  }
}
</script>

<style scoped>
.post {
  border: 1px solid #ccc;
  padding: 10px;
  margin: 10px 0;
  text-align: left;
}

button {
  margin: 5px;
}
</style>