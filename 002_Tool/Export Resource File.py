import os
import shutil
from datetime import datetime, timedelta

# Chemins absolus des dossiers source et destination
source_dir = os.path.join(os.path.dirname(__file__), 'resource')
dest_dir = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', '001_Game', 'resource'))

# Calculer le temps: actuel moins une heure
time_threshold = datetime.now() - timedelta(hours=1)

# Fonction pour copier les fichiers modifiés dans la dernière heure
def copy_recent_files(source, destination):
    files_checked = 0
    total_files = 75000
    
    for root, dirs, files in os.walk(source):
        for file in files:
            file_path = os.path.join(root, file)
            file_mod_time = datetime.fromtimestamp(os.path.getmtime(file_path))

            if file_mod_time > time_threshold:
                # Construire le chemin de destination
                rel_path = os.path.relpath(file_path, source)
                dest_path = os.path.join(destination, rel_path)
                
                # Créer le dossier de destination s'il n'existe pas
                os.makedirs(os.path.dirname(dest_path), exist_ok=True)
                
                # Copier le fichier
                shutil.copy2(file_path, dest_path)
                print(f"Copie de {file_path} à {dest_path}")

            # Afficher la progression en effaçant la ligne précédente
            files_checked += 1
            percent = (files_checked / total_files) * 100
            print(f"\r({percent:.2f}% complet)", end="")

    # Nettoyer la ligne de progression après la fin de la boucle
    print("\rProgression terminée.")

# Lancer la copie
copy_recent_files(source_dir, dest_dir)
