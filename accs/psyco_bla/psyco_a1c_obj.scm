	� G   r�       �VmoE��H����.wg'*7Hn���تT!��{�����:�U"���UQ��
E�hB|�x�/�l%�����8I[�����=3��̳�s7�9Y�$l&��KV�q�X� "͚ӈWN�� b1G�YL�&�ؚx
n<�����1�(��B B�sRk�av�����1�D���(\=5���Y�$��<���,��7!���Mv91JCܣ!M�U߇!q9�P�4F8I���(�c���W�!Q��$��h���d��(�2����&v��\����	�G�����p�I�*�M�a���i�5>�K�#�j���I���Q^�\���5K׶��غ/�~[����"}$ҝ��7D��3hR#��������xjA�h�]߯��{�A/c��%�c��y	]�:�*�$�^��H�B��l����9�>���W�v9Ƀ�)YԢ���i��}�����(�z��Cj-�s;D&��Wq�F�85�)�U�֚^�nz$���m|�BZ�1u�Z�\�������훃�����Ez_��@wp����o?�?����=�~&6o����L���� �!t���<�*3GAOg4tV9p��n�z#�泈\.��󘷭���XM���3̑�޽.�8�<` �#Vr���?Hi��2��Q�Xi�$l�Q0QX�$:�s�mgr��~�:�􇽯������G�C�ߓ��`w�˯ ��z����/Dz[l~,�oEz]l��G`��Xf����͒�躬�*�	�x�m�7|r�.�*"��1]6%V���Z�\V��}��@�6�M�������-y;j-���ų#`� &S�T�eUs�n��^V#����<��!���6�9v;0/e=n��Z�1^�ǚ*{&*;ΐ�X҂�|���ē�5�$=�J\�9q6�XM�C�^S�m�7��<�[i4�C�xr�Jo���qދ䪢�Y�,�����2�O� ?�m�vf,P{<S�חWj�J����{6��^����q�ԩ>����[>n%uv�5�Ga.W�.��X��2�P]:7_��]XZ�5�1	c�x�Li}��e�:O��p86b�!�����&���*�����_z�w����y6t�2�é�
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_bla/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            �X� �D /accs/psyco_bla/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ��y                        �  