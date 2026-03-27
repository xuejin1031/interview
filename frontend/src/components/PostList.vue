<template>
  <div class="post-list">
    <h3 class="section-title">所有發文</h3>
    <div v-for="post in posts" :key="post[0]" class="post card">
      <div class="post-header">
        <strong class="post-author">{{ post[4] }} ({{ post[5] }})</strong>
        <small class="post-date">{{ new Date(post[3]).toLocaleString() }}</small>
      </div>
      <p class="post-content">{{ post[1] }}</p>
      <img v-if="post[2]" :src="post[2]" alt="Post image" class="post-image">
      <div class="post-actions" v-if="canEdit(post)">
        <button class="btn btn-secondary btn-sm" @click="editPost(post)">編輯</button>
        <button class="btn btn-danger btn-sm" @click="deletePost(post[0])">刪除</button>
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
      return this.$root.currentUser && this.$root.currentUser.email === post[5];
    },
    editPost(post) {
      const newContent = prompt('編輯內容:', post[1]);
      const newImage = prompt('編輯圖片 URL:', post[2] || '');
      if (newContent !== null) {
        this.updatePost(post[0], newContent, newImage);
      }
    },
    async updatePost(id, content, image) {
      try {
        const response = await fetch(`http://localhost:8080/api/posts/${id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ content, image })
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
.post-list {
  margin-top: 2rem;
}

.section-title {
  margin-bottom: 1.5rem;
  color: #333;
  font-weight: 300;
}

.post {
  margin-bottom: 1.5rem;
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  padding: 1.5rem;
}

.post-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.post-author {
  font-size: 1.1rem;
  color: #007bff;
}

.post-date {
  color: #6c757d;
  font-size: 0.9rem;
}

.post-content {
  margin-bottom: 1rem;
  line-height: 1.6;
}

.post-image {
  max-width: 100%;
  height: auto;
  border-radius: 5px;
  margin-bottom: 1rem;
}

.post-actions {
  margin-bottom: 1rem;
  display: flex;
  gap: 0.5rem;
}

.btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 0.9rem;
  transition: all 0.3s ease;
}

.btn-sm {
  padding: 0.4rem 0.8rem;
  font-size: 0.8rem;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-secondary:hover {
  background-color: #545b62;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
}

.btn-danger:hover {
  background-color: #c82333;
}
</style>