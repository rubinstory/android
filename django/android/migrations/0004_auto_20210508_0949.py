# Generated by Django 3.2 on 2021-05-08 09:49

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('android', '0003_auto_20210508_0945'),
    ]

    operations = [
        migrations.AddField(
            model_name='comment',
            name='medicine',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='android.medicine'),
        ),
        migrations.AlterField(
            model_name='comment',
            name='text',
            field=models.TextField(null=True),
        ),
    ]
