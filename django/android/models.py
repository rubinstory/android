from django.db import models
from django.core.validators import MaxValueValidator, MinValueValidator

class User(models.Model):
	name = models.CharField(max_length = 200)
	password = models.CharField(max_length = 200)
	image = models.ImageField(blank=True, null=True)
	def __str__(self):
		return self.name

class Medicine(models.Model):
	name = models.CharField(max_length = 200)
	explanation = models.TextField()
	rate = models.FloatField(validators=[MinValueValidator(0.0), MaxValueValidator(5.0)],)
	category = models.CharField(max_length = 200, 
		choices = (('headache', '두통'), 
			('stomachache', '복통'), 
			('abrasion', '찰과상'), 
			('dermatitis', '피부염'),
			('etc', '기타')),
		default = 'etc'
	)
	image = models.ImageField(blank=True, null=True)
	def __str__(self):
		return self.name

class Comment(models.Model):
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	medicine = models.ForeignKey(Medicine, related_name="comments", on_delete=models.CASCADE, null=True)
	text = models.TextField(null=True)
	rate = models.FloatField(validators=[MinValueValidator(0.0), MaxValueValidator(5.0)],
							 default=0.0)

	def __str__(self):
		return self.user.name + "(" + str(self.medicine.name) + ")"

		