	� G   r�       �VmkG�,C��V�rr�w'�"���Ʀ��Z"J1�Ӟ����yo�X����4)��BH�h�[Zں�~	}�/]d���ݓd;I㦥���N���33������x�㨙��[�Ucܬy�ļrj��1eu(��XM����3p��y��~�x.��2x4 �:'�&f8���%M�F�ک�\@��"ǡN�ଋM$�$��Z�mҫ@ȱQ�>�HҮĈ��	�2�1v��(:�r<F�A��2 j�3I����/AVh��3y���Ď��GWZ۝���J�0_A1Mǜt�Rل@&ʫ�Vhー$�8R�&��q@��/��%�u���H[�tm[ln��b�g�u��z*��"���~S���A|�$5��03
*R�2�C������AP9���>	�^��K�e8���uUdI��^�����ri�s�}T�����r�S��E���,j?s	�qF�i�Z�v�L0�^�m��� <��Ve�5���.�����m|�bZ�1u�Z���F������[{�v�����H��������~:x�G���"�Ll��#���y}G' "C�La�y�Df��h �r�n%݆�F�+,�1�R2��\޶�Ks�b5	+h??�.G�Sz��\qy�
@Sr���?Hi�0�v�(��Xi8j�Q0QX�$:�s�mgr����rt��_��?��w���}�_v��݃/����=8{�|!�;b�c�~+�b�H?k��2�7-o��O�e�VM��+�lk.#n#�G�b�"B�eSb{���e�K�ׇʴ�m�m�wo��ݒ��֒�,\:7�`2UHu\V�a�0'��91��b���}���l:S�\�]��r��&��)�Ump���o���8�%-(�Gy:N<I�qCN�3� ��s,'Ά������Mx��p��+�F~�b_n_�u�(�X�*����0ϒ.������|��y�kc�33�@�l�Z�/��.֕RKM�l��FQ��کS;x��9ַ�����$>��\=���b����Bu��|}~vaiq���$���ٳ�i������<Cz���`����8��L���*�����_z�w���G�?�	��)���ܜ
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_gre/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            ��� �D /accs/psyco_gre/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ���                        �  