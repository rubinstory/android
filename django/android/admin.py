from django.contrib import admin
from .models import User, Medicine, Comment
# Register your models here.
admin.site.register(User)
admin.site.register(Medicine)
admin.site.register(Comment)