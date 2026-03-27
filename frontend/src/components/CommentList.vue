<template>
  <div class="comment-list">
    <div v-for="comment in comments" :key="comment.id" class="comment">
      <p><strong>{{ comment.userId }}</strong>: {{ comment.content }}</p>
      <small>{{ new Date(comment.createdAt).toLocaleString() }}</small>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CommentList',
  props: ['postId'],
  data() {
    return {
      comments: []
    }
  },
  mounted() {
    this.loadComments();
  },
  methods: {
    async loadComments() {
      try {
        const response = await fetch(`http://localhost:8080/api/comments/post/${this.postId}`);
        this.comments = await response.json();
      } catch (error) {
        console.error('Load comments error:', error);
      }
    }
  },
  watch: {
    postId() {
      this.loadComments();
    }
  }
}
</script>

<style scoped>
.comment {
  border-left: 2px solid #ccc;
  padding-left: 10px;
  margin: 5px 0;
  text-align: left;
}
</style>