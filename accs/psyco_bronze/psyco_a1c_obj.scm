	� G   t�       �Vmo�T��J���S\�I`ʂ��e�`�h�IB�c_'�ؾ�}�5�Js���I &M���$Z�_&^�W�����:I�m��'�(��s�s�y�Ǟ����n�Zv�H���F-��[wb�ʩ	D4f�C�����6��[�O�����8�A�u���! !S9Ɉu�07��G}z����i���&r>u:K*y��]�#�[�%9�&��۶K� !�Zi�{$$I���#�#J��N�hY���g�	��ː��Cj4yna�B=��T��8�%}��6c~��NH<l$N����&��O[F��C��eY���A^po+Y���ȧ.���ǩ	s��a7T�,c��;|�>���o�:���t�����o�t<�������������xfA���®�W��ｏ@�������2�c��]�:�2� V^�TC!c�Z�\GJ��t׆���+v���_,r��x�K�����\�h�Q���Cj%�S�D$�v��Z�83O(���VO�y�m�@���6�r�-`�*[��OC^ə������{���xz���@w0�����o?>�c��=�~�7o��!�L���� �!t���<�"2GA_e4TV9p7�nS�c�%��_.i���6k+�yp1\���S�!�)��#.�XG<` �#�r���?Hi��"��q��Xi�8l��V�QX�4:�r0Mkz����rt��_�<���w����ֿ'�����_8��.���O���m��	O���u�y���5kc�ٛ��7K�#�n���d�#�����cp�d	�ٲ.��9[V
����#i��6�4{���l��qk	M/��0�2�<.k�`�WU�<3��e�0&M@�y0h5�t��ٌ�N�⥬�u4YoS�j�`3eOGe�rKJP���t<�0mGM1LO˳����:R/��Q����6a��m��6��!�=���7�}L�GbUQއ4���tc�aZ�(���u�6v:Ցʏ��Fce��x�!�*r�gs(�5�rΟ�O��s���2���ۭ�A�S�xFs���;K�˫s���s�����9=��0�gΔf�G�Z����3�Gл����9/���^T@B�?���ｇ����-$��7ߔ	�,c0�
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_bronze/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
 f x y isServer ()Z
 f { | getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f ~  '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 	playSound
 d � 3 getArgv ()Ljava/lang/Object;
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY2 (F)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/Position; � dir	 � � 	 � R  getDir (Lkotori/geom/Vector3D;)F
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � � min
 � � �B4   getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �B�   	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � startScript (Ljava/lang/String;)Z
 � � �-            �X� �D /accs/psyco_bronze/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ��C                        �  