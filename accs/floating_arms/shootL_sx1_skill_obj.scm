	� G %/accs/floating_arms/floating_arms.mot ��       �Wmo�V�\$��]���:��6�P(R�k*LS�87�]���7%E��1
h�*�`�A�h|A�}�?���~�_ع�ڎ���bS��>��>��sN���������Q���i��+E�%MZؾ�XMǥ��P�%JE��2��\Î���NR&6$����M�O�b�1���#>s� ��TJ�߾m���$�H��RU7=�.b��W����b)ǉ�c4Ql�0%궰��/;m�'e5ms΁vv<K,p�e�SN�o��īO�&�mc�p_⇆�u� ������	�?�8�����>;�Ru��Ԫ��ص��Z����)y�l�k�T<�J�2Jq\r�tQND����J�x+X|,.����� ���^�p�T%��;�])�a9	�;��>*��[�YHҿ���Vǃ��� <����t�(y�L���h�iH��@@
�e$0���Qj(�n7M��Kmx�F��H�L�R(�1`B�������P%��Qc����vksR���i�{u�(��ȭ��*�N���L�b�U������W��k��w�zモ���x���&υSq5iQ=�p*�G;w2�a"x�!Q��(�:BCD�� ��ʒ��諸���[�5������`�'��aD&�A����Kq��=hR�W�e�x���E�c������IYK�G.�-���]�é̙�����뇊��^|�[�(�k���`�F�����*�-�ρcGuZW\��	�/3�}�#D����=4-���o*JE"���'����,g����1p��\�N�RG��T�H?:cB�6�"�ݙ�X�(�ɜ�a������g-�Gr]�Hw�t���:���K�WU-3��_��.~�]�?Y}p��t���i�y���} ��,C�_��WX1����`a)��$��٣��ݬ�6JɣQ�r!q8��D�i2�%�Eli"/sbZ�ȇUE�T��:⪍tCK�l�=���{���O�n?}��#�wo�R��K&�yn�k���:��{a_P�W1L�f�iY��_~ֻ��_�G0(1 ��o��������,\�W��s>QQg6E$�̩3�b���_`�ǞUW"SZ��Y�;6"]��eG%2�M���Hba�p�ħ�H�7u�B,!������g�x^��5= YUO�'/Ez��&�G�� W�悪~vhf�p��cTx9ly3�[V�rXgԓ#B�5��|�DC���|S��pGߴ�w7~�3Q�_��lq�%G\�q)|	
~��u+����#e,eeH�<z�4�N�w���q4F�lᚦT76Vv$IS&�]\��ˋ҂����r��Vk-8l��x�h�5aob:2�2�. ��a���.���[���I�Z'�3�𚂧R�"*����v�_��{�(J�d����)�-D�� <���FB����A�.�W	�W�Z��^�2!C#c"��dدd��8['�sX��)�]���Z�K�_Mj�~-^�?t�O,y�OUB�b_GB�yn��=K��8����   }  Lga2/stage/AmpedScript; e start floating_arms_explode_x_execute 
shotCalc_x (Lkotori/geom/Vector3D;)V shot_x (Lkotori/geom/Quaternion;)V broken 
setVisible (Z)V
 f n o setShadowState
 d q 3 lockItemScript (ZZ)V
 f s t
 H 0 3 lockTimeCount
 f w o finishAllConnections
 d y 1 clearGenerate
 d { 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d } ~ setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D (/accs/floating_arms/shootL_sx1_skill.scm�            ?�  ?�  ?�               start2 isServer ()Z
 f � � getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � length ()F
 � � �@�  �� kotori.geom.Quaternion?}�5>�e         Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � �
 f � �C4  C�  �4  	 � R  (FF)F
 � � � min
 � � �B4  A�  ��?�               Lkotori/geom/Quaternion; � clone
 � � �=��� getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � Lkotori/gfx/KGLScriptModel; � rotate (Lkotori/geom/Quaternion;I)V
 � � ���?}�5>�e         startScript (Ljava/lang/String;)Z
 � � � beam vibe (FFFFI)V
 d � �<#�
 	playSound
 d � 3�X��D /efx/gun/shell_floating_x.scm�        ����@@  @@  @@              � hit_be  d� �    a��       ,�>�         	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-             beam_end efx_end f  g 1  }*� pW*� rW*� uW*d� vW*� uW*� xW*� zW*� |W**��� � �W* ȶ vW*� ��  L*� �M,� 
*,� �L*h+� �W*� vW*� �W*� �W�  h 1  *� �L*� vW*+� W*d� vW*� W�  i j �*� �M,��+��*� �N+-� �W+� �8��� *,� vW*�� W�+� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8��� �f8� ��� 
�b8+� �8+� �+� �8		� �� �� �Zd�8

�� #b
j8b
j�
� ��nj8b8

�f8�:� �:6� /�`�jj8� �W,� �W*
� vW�W
���6� 0�`�jj8� �W,� �W*
� vW�W
���*d� vW� �W,� �W
� �W*� W� *,� vW*�� W�  k l  p*� �M,� 
,ж �W*^� vW*�\�� �W* ϶ �W�N6� '*+� �W*�-� �W*� �W* ȶ vW�W���,� 
,߶ �W�  m 1  @*� uW*� xW*� vW*� �L+� 
+� �W*�� vW*�� vW*� �W*� �W� FPk�    ?S(6���p?�L^?>t�?�[�  