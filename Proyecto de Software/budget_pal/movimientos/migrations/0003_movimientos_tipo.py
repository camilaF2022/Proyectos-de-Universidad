# Generated by Django 4.2 on 2023-06-07 00:33

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('movimientos', '0002_alter_movimientos_id'),
    ]

    operations = [
        migrations.AddField(
            model_name='movimientos',
            name='tipo',
            field=models.CharField(blank=True, choices=[('ingreso', 'Ingreso'), ('egreso', 'Egreso')], max_length=10),
        ),
    ]
