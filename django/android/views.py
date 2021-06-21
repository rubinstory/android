from django.shortcuts import render
from rest_framework.response import Response
from rest_framework import status, viewsets
from .serializers import UserSerializer, MedicineSerializer, CommentSerializer
from .models import User, Medicine, Comment
from django.db.models import Count

class UserViewset(viewsets.ModelViewSet):
	queryset = User.objects.all()
	serializer_class = UserSerializer
	
	def get_paginated_response(self, data):
		return Response(data)

class MedicineViewset(viewsets.ModelViewSet):
	queryset = Medicine.objects.all()
	serializer_class = MedicineSerializer

	def get_queryset(self):
		qs = super().get_queryset()
		search = self.request.query_params.get('category', '')
		if search:
			qs = qs.filter(category__icontains=search)
		return qs

	def get_paginated_response(self, data):
		return Response(data)


class CommentViewset(viewsets.ModelViewSet):
	queryset = Comment.objects.all()
	serializer_class = CommentSerializer

	def create(self, request):
		print("----------")
		print(request)
		serializer = self.get_serializer(data=request.data)
		serializer.is_valid(raise_exception=True)
		self.perform_create(serializer)
		headers = self.get_success_headers(serializer.data)

		user_rate = float(request.data['rate'])
		curr_medicine = Medicine.objects.get(pk=int(request.data['medicine']))
		medicine_list = Medicine.objects.annotate(number_of_comments=Count('comments'))
		curr_num = 0
		for medicine in medicine_list:
			if medicine == curr_medicine:
				curr_num = medicine.number_of_comments - 1
				break
		new_rate = curr_medicine.rate * curr_num + user_rate
		curr_num += 1
		new_rate /= curr_num
		curr_medicine.rate = new_rate
		curr_medicine.save()
		return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)

	def get_paginated_response(self, data):
		return Response(data)

