# Generated by Django 3.2 on 2021-05-08 09:45

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('android', '0002_medicine_type_user'),
    ]

    operations = [
        migrations.AlterField(
            model_name='medicine',
            name='rate',
            field=models.FloatField(),
        ),
        migrations.CreateModel(
            name='Comment',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('text', models.TextField()),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='android.user')),
            ],
        ),
    ]
