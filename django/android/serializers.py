from rest_framework import serializers
from .models import Post, User, Medicine, Comment

class PostSerializer(serializers.ModelSerializer):
	class Meta:
		model = Post
		fields = ('title', 'text')


class CommentSerializer(serializers.ModelSerializer):
	class Meta:
		model = Comment
		fields = ('user', 'medicine', 'text', 'rate')
		#fields = ('id', 'text')

class UserSerializer(serializers.ModelSerializer):
	class Meta:
		model = User
		fields = ('name', 'password', 'image', 'id')

class MedicineSerializer(serializers.ModelSerializer):
	comments = CommentSerializer(many=True, read_only=True)
	class Meta:
		model = Medicine
		fields = ('id', 'name', 'explanation', 'rate', 'category', 'comments', 'image')