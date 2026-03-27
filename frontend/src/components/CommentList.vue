<template>
  <div class="comment-list">
    <div v-for="comment in comments" :key="comment.id" class="comment">
      <div class="comment-header">
        <strong class="comment-author">{{ comment.userId }}</strong>
        <small class="comment-date">{{ new Date(comment.createdAt).toLocaleString() }}</small>
      </div>
      <p class="comment-content">{{ comment.content }}</p>
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
.comment-list {
  margin-top: 1rem;
}

.comment {
  background: #f8f9fa;
  border-radius: 5px;
  padding: 0.75rem;
  margin-bottom: 0.5rem;
  border-left: 3px solid #007bff;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.5rem;
}

.comment-author {
  font-size: 0.9rem;
  color: #007bff;
}

.comment-date {
  color: #6c757d;
  font-size: 0.8rem;
}

.comment-content {
  margin: 0;
  line-height: 1.4;
}
</style>